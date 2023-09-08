<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入供应商编号" suffix-icon="el-icon-search" v-model="supplierNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入供应商姓名" suffix-icon="el-icon-message" class="ml-5" v-model="supplierName"></el-input>
            <el-input style="width: 200px" placeholder="请输入联系人姓名" suffix-icon="el-icon-position" class="ml-5" v-model="contactPersonName"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="supplierNo" label="供应商编号" width="80"></el-table-column>
            <el-table-column prop="supplierName" label="供应商名称" width="80"></el-table-column>
            <el-table-column prop="contactPersonName" label="联系人姓名" width="80"></el-table-column>
            <el-table-column prop="contactTelephone" label="联系电话" width="100"></el-table-column>
            <el-table-column prop="email" label="电子邮件" width="150"></el-table-column>
            <el-table-column prop="supplierPlace" label="所在地址" width="80"></el-table-column>
            <el-table-column prop="supplyGoodsList" label="供应商品列表" width="150"></el-table-column>
            <el-table-column prop="contractStartDate" label="合同起始日期" width="90"></el-table-column>
            <el-table-column prop="contractOverDate" label="合同结束日期" width="90"></el-table-column>
            <el-table-column label="操作" width="200" align="center" key="slot">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.supplierNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="供应商信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="100px" size="big">
                <el-form-item label="供应商编号">
                    <el-input v-model="form.supplierNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="供应商名称">
                    <el-input v-model="form.supplierName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系人姓名">
                    <el-input v-model="form.contactPersonName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="form.contactTelephone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="电子邮件">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="所在地址">
                    <el-input v-model="form.supplierPlace" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="供应商品列表">
                    <el-input v-model="form.supplyGoodsList" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="合同起始日期">
                    <el-input v-model="form.contractStartDate" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="合同结束日期">
                    <el-input v-model="form.contractOverDate" autocomplete="off"></el-input>
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
            supplierNo: "",
            supplierName: "",
            contactPersonName: "",
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
            this.request.get("/supplier/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    supplierNo: this.supplierNo,
                    supplierName:this.supplierName,
                    contactPersonName: this.contactPersonName
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/supplier", this.form).then(res => {
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
        del(index,supplierNo) {
            this.request.delete("/supplier/" + supplierNo).then(res => {
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
            let supplierNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                supplierNos.push(i.supplierNo);
            }
            this.request.post("/supplier/del/batch", supplierNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.supplierNo = ""
            this.supplierName = ""
            this.contactPersonName = ""
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
