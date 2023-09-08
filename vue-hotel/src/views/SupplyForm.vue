<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入采购编号" suffix-icon="el-icon-search" v-model="supplyFormNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入采购日期（xxxx-xx-xx）" width="200" suffix-icon="el-icon-message" class="ml-5" v-model="purchaseDate"></el-input>
            <el-input style="width: 200px" placeholder="请输入采购负责人" suffix-icon="el-icon-position" class="ml-5" v-model="purchaseManager"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="supplyFormNo" label="采购编号" width="89"></el-table-column>
            <el-table-column prop="goodsNo" label="货物编号" width="80"></el-table-column>
            <el-table-column prop="purchaseDate" label="采购日期" width="80"></el-table-column>
            <el-table-column prop="purchaseManager" label="采购负责人" width="80"></el-table-column>
            <el-table-column prop="purchaseAmount" label="采购数量" width="80"></el-table-column>
            <el-table-column prop="purchasePrice" label="采购金额"></el-table-column>
            <el-table-column label="操作" width="200" align="center" key="slot">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.supplyFormNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="采购信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="采购编号">
                    <el-input v-model="form.supplyFormNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="货物编号">
                    <el-input v-model="form.goodsNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="采购日期">
                    <el-input v-model="form.purchaseDate" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="采购负责人">
                    <el-input v-model="form.purchaseManager" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="采购数量">
                    <el-input v-model="form.purchaseAmount" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="采购金额">
                    <el-input v-model="form.purchasePrice" autocomplete="off"></el-input>
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
    name: "CateringOrder",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            supplyFormNo: "",
            purchaseDate: "",
            purchaseManager: "",
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
            this.request.get("/supplyform/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    supplyFormNo: this.supplyFormNo,
                    purchaseDate: this.purchaseDate,
                    purchaseManager: this.purchaseManager,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/supplyform", this.form).then(res => {
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
            console.log(index)
            console.log(row)
            this.form = JSON.parse(JSON.stringify(row))
            this.dialogFormVisible = true
        },
        del(index,orderNo) {
            this.request.delete("/supplyform/" + orderNo).then(res => {
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
            this.multipleSelection = val;
            console.log(this.multipleSelection);

        },
        delBatch() {
            if(this.multipleSelection.length ==0 )
            {
                this.$message("你还没有勾选删除项")
                return;
            }
            let orderNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                orderNos.push(i.orderNo);
            }
            this.request.post("/supplyform/del/batch", orderNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.supplyFormNo = ""
            this.purchaseDate = ""
            this.purchaseManager = ""
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
