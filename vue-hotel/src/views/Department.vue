<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入部门编号" suffix-icon="el-icon-search" v-model="deptNO"></el-input>
            <el-input style="width: 200px" placeholder="请输入部门名称" suffix-icon="el-icon-message" class="ml-5" v-model="deptName"></el-input>
            <el-input style="width: 200px" placeholder="请输入部门负责人" suffix-icon="el-icon-position" class="ml-5" v-model="deptManager"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>


        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>


        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="deptNo" label="部门编号" width="80"></el-table-column>
            <el-table-column prop="deptName" label="部门名称" width="80"></el-table-column>
            <el-table-column prop="deptManager" label="部门负责人" width="80"></el-table-column>
            <el-table-column prop="deptTelephone" label="部门电话" width="100"></el-table-column>
            <el-table-column prop="deptEmpNum" label="部门员工数目" width="80"></el-table-column>
            <el-table-column prop="deptDescription" label="部门描述" ></el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.deptNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="部门信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="部门编号">
                    <el-input v-model="form.deptNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门名称">
                    <el-input v-model="form.deptName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门负责人">
                    <el-input v-model="form.deptManager" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门电话">
                    <el-input v-model="form.deptTelephone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门员工数目">
                    <el-input v-model="form.deptEmpNum" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门描述">
                    <el-input v-model="form.deptDescription" autocomplete="off"></el-input>
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
    name: "Department",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            deptNO: "",
            deptName: "",
            deptManager: "",
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
            this.request.get("/department/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    deptNo: this.deptNO,
                    deptName: this.deptName,
                    deptManager: this.deptManager,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/department", this.form).then(res => {
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
        del(index,deptNo) {
            this.request.delete("/department/" + deptNo).then(res => {
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
            let deptNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                deptNos.push(i.deptNo);
            }
            this.request.post("/department/del/batch", deptNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.deptNO = ""
            this.deptName = ""
            this.deptManager = ""
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
