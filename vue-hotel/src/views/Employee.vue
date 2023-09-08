<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入员工编号" suffix-icon="el-icon-search" v-model="empNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入员工姓名" suffix-icon="el-icon-message" class="ml-5" v-model="memberName"></el-input>
            <el-input style="width: 200px" placeholder="请输入员工职务" suffix-icon="el-icon-position" class="ml-5" v-model="empPostName"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>


        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="empNo" label="员工编号" width="80"></el-table-column>
            <el-table-column prop="empCardID" label="身份证号码" width="150"></el-table-column>
            <el-table-column prop="memberName" label="姓名" width="80"></el-table-column>
            <el-table-column prop="memberSex" label="性别" width="80"></el-table-column>
            <el-table-column prop="hireTime" label="入职时间" width="90"></el-table-column>
            <el-table-column prop="empGrade" label="职级" width="80"></el-table-column>
            <el-table-column prop="empPostName" label="职务名称" width="80"></el-table-column>
            <el-table-column prop="empBirthday" label="出生年月" width="90"></el-table-column>
            <el-table-column prop="empBankCardID" label="银行卡号" width="155"></el-table-column>
            <el-table-column prop="empDepart" label="所属部门" width="100"></el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.empNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="员工信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="员工编号">
                    <el-input v-model="form.empNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="身份证号码">
                    <el-input v-model="form.empCardID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.memberName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input v-model="form.memberSex" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入职时间">
                    <el-input v-model="form.hireTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="职级">
                    <el-input v-model="form.empGrade" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="职务名称">
                    <el-input v-model="form.empPostName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出生年月">
                    <el-input v-model="form.empBirthday" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="银行卡号">
                    <el-input v-model="form.empBankCardID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="所属部门">
                    <el-input v-model="form.empDepart" autocomplete="off"></el-input>
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
    name: "Employee",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            empNo: "",
            memberName: "",
            empPostName: "",
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
            this.request.get("/employee/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    empNo: this.empNo,
                    memberName: this.memberName,
                    empPostName: this.empPostName,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/employee", this.form).then(res => {
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
        del(index,empNo) {
            this.request.delete("/employee/" + empNo).then(res => {
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
            let empNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                empNos.push(i.empNo);
            }
            this.request.post("/employee/del/batch", empNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.empNo = ""
            this.memberName = ""
            this.empPostName = ""
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
