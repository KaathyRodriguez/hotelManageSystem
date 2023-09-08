<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入货物编号" suffix-icon="el-icon-search" v-model="goodsNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入货物名称" suffix-icon="el-icon-message" class="ml-5" v-model="goodsName"></el-input>
            <el-input style="width: 200px" placeholder="请输入货物类型" suffix-icon="el-icon-position" class="ml-5" v-model="goodsType"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="goodsNo" label="货物编号" width="100"></el-table-column>
            <el-table-column prop="goodsName" label="货物名称" width="120"></el-table-column>
            <el-table-column prop="goodsType" label="货物类型" width="120"></el-table-column>
            <el-table-column prop="goodsNum" label="货物数量" width="100"></el-table-column>
            <el-table-column prop="goodsCost" label="货物单价" width="100"></el-table-column>
            <el-table-column prop="goodsLimit" label="货物最低量"></el-table-column>
            <el-table-column label="操作"  width="300" align="center">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.goodsNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="客户信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="货物编号">
                    <el-input v-model="form.goodsNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="货物名称">
                    <el-input v-model="form.goodsName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="货物类型">
                    <el-input v-model="form.goodsType" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="货物数量">
                    <el-input v-model="form.goodsNum" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="货物单价">
                    <el-input v-model="form.goodsCost" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="货物最低量">
                    <el-input v-model="form.goodsLimit" autocomplete="off"></el-input>
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
    name: "Goods",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            goodsNo: "",
            goodsName: "",
            goodsType: "",
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
            this.request.get("/goods/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    goodsNo: this.goodsNo,
                    goodsName: this.goodsName,
                    goodsType: this.goodsType,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/goods", this.form).then(res => {
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
        handleEdit(index,row) {
            this.form = JSON.parse(JSON.stringify(row))
            this.dialogFormVisible = true
        },
        del(index,goodsNo) {
            this.request.delete("/goods/" + goodsNo).then(res => {
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
            let goodsNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                goodsNos.push(i.goodsNo);
            }
            this.request.post("/goods/del/batch", goodsNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.goodsNo = ""
            this.goodsName = ""
            this.goodsType = ""
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
