<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入仓库编号" suffix-icon="el-icon-search" v-model="warehouseNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入仓库名" suffix-icon="el-icon-message" class="ml-5" v-model="warehouseName"></el-input>
            <el-input style="width: 200px" placeholder="请输入仓库负责人" suffix-icon="el-icon-position" class="ml-5" v-model="warehouseManager"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="warehouseNo" label="仓库编号" width="80"></el-table-column>
            <el-table-column prop="warehouseName" label="仓库名" width="100"></el-table-column>
            <el-table-column prop="warehouseManager" label="仓库负责人" width="100"></el-table-column>
            <el-table-column prop="warehousePlace" label="仓库位置" width="120"></el-table-column>
            <el-table-column prop="storeGoodsList" label="存储货物列表" width="150"></el-table-column>
            <el-table-column prop="warehouseState" label="仓库状态" width="100"></el-table-column>
            <el-table-column prop="warehouseSafeLevel" label="仓库安全等级" width="80"></el-table-column>
            <el-table-column label="操作" align="center" key="slot">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.warehouseNo)">删除<i class="el-icon-remove-outline"></i></el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[2, 5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>

        <el-dialog title="仓库信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="100px" size="big">
                <el-form-item label="仓库编号">
                    <el-input v-model="form.warehouseNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库名">
                    <el-input v-model="form.warehouseName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库负责人">
                    <el-input v-model="form.warehouseManager" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库位置">
                    <el-input v-model="form.warehousePlace" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="存储货物列表">
                    <el-input v-model="form.storeGoodsList" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库状态">
                    <el-input v-model="form.warehouseState" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库安全等级">
                    <el-input v-model="form.warehouseSafeLevel" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
    name: "Supplier",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            warehouseNo: "",
            warehouseName: "",
            warehouseManager: "",
            form: {},
            dialogFormVisible: false,
            multipleSelection: []
        }
    },
    created() {
        this.load()
    },
    methods: {
        load() {
            this.request.get("/warehouse/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    warehouseNo: this.warehouseNo,
                    warehouseName:this.warehouseName,
                    warehouseManager: this.warehouseManager
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/warehouse", this.form).then(res => {
                if (res.code === '200') {
                    this.$message.success("保存成功")
                    this.dialogFormVisible = false
                    this.load()
                } else {
                    this.$message.error("保存失败")
                }
            })
        },
        handleAdd() {
            this.dialogFormVisible = true
            this.form = {}
        },
        handleEdit(index, row) {
            this.form = JSON.parse(JSON.stringify(row))
            this.dialogFormVisible = true
        },
        del(index,warehouseNo) {
            this.request.delete("/warehouse/" + warehouseNo).then(res => {
                if (res.code === '200') {
                    this.$message.success("删除成功")
                    this.load()
                } else {
                    this.$message.error("删除失败")
                }
            })
        },
        handleSelectionChange(val) {
            console.log(val)
            this.multipleSelection = val
        },
        delBatch() {
            if(this.multipleSelection.length ==0 )
            {
                this.$message("你还没有勾选删除项")
                return;
            }
            let warehouseNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                warehouseNos.push(i.warehouseNo);
            }
            this.request.post("/warehouse/del/batch", warehouseNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.warehouseNo = ""
            this.warehouseName = ""
            this.warehouseManager = ""
            this.load()
        },
        handleSizeChange(pageSize) {
            console.log(pageSize)
            this.pageSize = pageSize
            this.load()
        },
        handleCurrentChange(pageNum) {
            console.log(pageNum)
            this.pageNum = pageNum
            this.load()
        }
    }
}
</script>


<style>
.headerBg {
    background: #eee!important;
}
</style>
