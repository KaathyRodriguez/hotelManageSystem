<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入客户编号" suffix-icon="el-icon-search" v-model="memberNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入客户姓名" suffix-icon="el-icon-message" class="ml-5" v-model="memberName"></el-input>
            <el-input style="width: 200px" placeholder="请输入性别" suffix-icon="el-icon-position" class="ml-5" v-model="memberSex"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="memberNo" label="客户编号" width="80"></el-table-column>
            <el-table-column prop="memberCardID" label="身份证号" width="155"></el-table-column>
            <el-table-column prop="memberName" label="姓名" width="80"></el-table-column>
            <el-table-column prop="memberSex" label="性别" width="80"></el-table-column>
            <el-table-column prop="memberLevel" label="会员等级" width="80"></el-table-column>
            <el-table-column prop="memberPoint" label="会员积分" width="80"></el-table-column>
            <el-table-column prop="memberTelephone" label="手机号"></el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.memberNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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
                <el-form-item label="客户编号">
                    <el-input v-model="form.memberNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="form.memberCardID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.memberName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input v-model="form.memberSex" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="会员等级">
                    <el-input v-model="form.memberLevel" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="会员积分">
                    <el-input v-model="form.memberPoint" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.memberTelephone" autocomplete="off"></el-input>
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
    name: "Customer",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            memberNo: "",
            memberName: "",
            memberSex: "",
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
            this.request.get("/customer/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    memberNo: this.memberNo,
                    memberName: this.memberName,
                    memberSex: this.memberSex,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/customer", this.form).then(res => {
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
        del(index,memberNo) {
            this.request.delete("/customer/" + memberNo).then(res => {
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
            let memberNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                memberNos.push(i.memberNo);
            }
            this.request.post("/customer/del/batch", memberNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.memberNo = ""
            this.memberName = ""
            this.memberSex = ""
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
