<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入入住编号" suffix-icon="el-icon-search" v-model="checkInFormNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入入住人编号" suffix-icon="el-icon-message" class="ml-5" v-model="memberNo"></el-input>
            <el-input style="width: 200px" placeholder="请输入入住状态" suffix-icon="el-icon-position" class="ml-5" v-model="state"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="checkInFormNo" label="入住编号" width="80"></el-table-column>
            <el-table-column prop="memberNo" label="入住人编号" width="80"></el-table-column>
            <el-table-column prop="memberCardID" label="入住人身份证号码" width="120"></el-table-column>
            <el-table-column prop="roomNo" label="入住房间号" width="80"></el-table-column>
            <el-table-column prop="livingPersonNum" label="入住人数" width="80"></el-table-column>
            <el-table-column prop="comingTime" label="入住时间" width="90"></el-table-column>
            <el-table-column prop="leaveTime" label="离开时间" width="90"></el-table-column>
            <el-table-column prop="roomAmount" label="房间价格" width="80"></el-table-column>
            <el-table-column prop="isFirst" label="是否首次入住" width="80"></el-table-column>
            <el-table-column prop="state" label="入住状态" ></el-table-column>
            <el-table-column label="操作" width="200" align="center" key="slot">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.checkInFormNo)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="入住信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="入住编号">
                    <el-input v-model="form.checkInFormNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入住人编号">
                    <el-input v-model="form.memberNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入住人身份证号码">
                    <el-input v-model="form.memberCardID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入住房间号">
                    <el-input v-model="form.roomNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入住人数">
                    <el-input v-model="form.livingPersonNum" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入住时间">
                    <el-input v-model="form.comingTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="离开时间">
                    <el-input v-model="form.leaveTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="房间价格">
                    <el-input v-model="form.roomAmount" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="是否首次入住">
                    <el-input v-model="form.isFirst" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="入住状态">
                    <el-input v-model="form.state" autocomplete="off"></el-input>
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
    name: "checkInForm",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            checkInFormNo: "",
            memberNo: "",
            state: "",
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
            this.request.get("/checkinform/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    checkInFormNo: this.checkInFormNo,
                    memberNo: this.memberNo,
                    state: this.state,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/checkinform", this.form).then(res => {
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
        del(index,checkInFormNo) {
            this.request.delete("/checkinform/" + checkInFormNo).then(res => {
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
            let checkInFormNos = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                checkInFormNos.push(i.checkInFormNo);
            }
            this.request.post("/checkinform/del/batch", checkInFormNos).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.checkInFormNo = ""
            this.memberNo = ""
            this.state = ""
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
