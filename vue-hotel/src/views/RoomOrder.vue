<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入客房预定编号" suffix-icon="el-icon-search" v-model="orderID"></el-input>
            <el-input style="width: 200px" placeholder="请输入预定人姓名" suffix-icon="el-icon-message" class="ml-5" v-model="memberName"></el-input>
            <el-input style="width: 200px" placeholder="请输入预定状态" suffix-icon="el-icon-position" class="ml-5" v-model="orderStatus"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="orderID" label="客房预定编号" width="80"></el-table-column>
            <el-table-column prop="orderTime" label="预定时间" width="90"></el-table-column>
            <el-table-column prop="memberName" label="预定人姓名" width="80"></el-table-column>
            <el-table-column prop="memberCardID" label="预定人身份证" width="150"></el-table-column>
            <el-table-column prop="orderRoomNo" label="预定房间号" width="80"></el-table-column>
            <el-table-column prop="orderRoomType" label="预定房间类型" width="80"></el-table-column>
            <el-table-column prop="orderComingTime" label="预定入住时间" width="90"></el-table-column>
            <el-table-column prop="orderAmount" label="预定金" width="80"></el-table-column>
            <el-table-column prop="orderStatus" label="预定状态"></el-table-column>
            <el-table-column label="操作" width="200" align="center" key="slot">
                <template #default="scope">
                    <el-button size="small" type="success" @click="handleEdit(scope.$index,scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-button size="small" type="danger" @click="del(scope.$index, scope.row.orderID)">删除<i class="el-icon-remove-outline"></i></el-button>
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

        <el-dialog title="客房预定信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="客房预定编号">
                    <el-input v-model="form.orderID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定时间">
                    <el-input v-model="form.orderTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定人姓名">
                    <el-input v-model="form.memberName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定人身份证">
                    <el-input v-model="form.memberCardID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定房间号">
                    <el-input v-model="form.orderRoomNo" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定房间类型">
                    <el-input v-model="form.orderRoomType" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定入住时间">
                    <el-input v-model="form.orderComingTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定金">
                    <el-input v-model="form.orderAmount" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预定状态">
                    <el-input v-model="form.orderStatus" autocomplete="off"></el-input>
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
    name: "RoomOrder",
    data() {
        return {
            serverIp: serverIp,
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            orderID: "",
            memberName: "",
            orderStatus: "",
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
            this.request.get("/roomorderform/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    orderID: this.orderID,
                    memberName: this.memberName,
                    orderStatus: this.orderStatus,
                }
            }).then(res => {
                this.tableData = res.data.records
                this.total = res.data.total

            })
        },
        save() {
            this.request.post("/roomorderform", this.form).then(res => {
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
        del(index,orderID) {
            this.request.delete("/roomorderform/" + orderID).then(res => {
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
            let orderIDs = [];
            for(let j = 0;j < this.multipleSelection.length;j++)
            {
                var i = this.multipleSelection[j];
                orderIDs.push(i.orderID);
            }
            this.request.post("/roomorderform/del/batch", orderIDs).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.orderID = ""
            this.memberName = ""
            this.orderStatus = ""
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
