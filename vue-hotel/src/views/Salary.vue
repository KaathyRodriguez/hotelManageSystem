<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入工资单号" suffix-icon="el-icon-search" v-model="salaryNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入员工编号" suffix-icon="el-icon-position" class="ml-5" v-model="empNo"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button type="warning" @click="add" class="ml-5">加薪<i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="salaryNo" label="工资单号" width="80"></el-table-column>
            <el-table-column prop="empNo" label="员工编号" width="80"></el-table-column>
            <el-table-column prop="baseSalary" label="基本工资" width="120"></el-table-column>
            <el-table-column prop="performanceSalary" label="绩效工资" width="120"></el-table-column>
            <el-table-column prop="deductSalary" label="扣除工资" width="120"></el-table-column>
            <el-table-column prop="bonus" label="奖金"></el-table-column>
            <el-table-column label="操作" width="200" align="center" key="slot">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.salaryNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="工资单信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="100px" size="big">
                <el-form-item label="工资单号">
                    <el-input v-model="form.salaryNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="员工编号">
                    <el-input v-model="form.empNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="基本工资">
                    <el-input v-model="form.baseSalary" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="绩效工资">
                    <el-input v-model="form.performanceSalary" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="扣除工资">
                    <el-input v-model="form.deductSalary" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="奖金">
                    <el-input v-model="form.bonus" autocomplete="off"></el-input>
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
    name: "Salary",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            salaryNo: "",
            empNo: "",
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
            this.request.get("/salary/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    salaryNo: this.salaryNo,
                    empNo:this.empNo
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/salary", this.form).then(res => {
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
        del(index,salaryNo) {
            this.request.delete("/salary/" + salaryNo).then(res => {
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
            let salaryNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                salaryNos.push(i.salaryNo);
            }
            this.request.post("/salary/del/batch", salaryNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.salaryNo = ""
            this.empNo = ""
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
        },
        add() {
            this.request.post("/salary/add").then(res=>{
                if (res.code === '200') {
                    this.$message.success("加薪成功")
                    this.load()
                } else {
                    this.$message.error("加薪失败")
                }
            })
        }
    }
}
</script>


<style>
.headerBg {
    background: #eee!important;
}
</style>
