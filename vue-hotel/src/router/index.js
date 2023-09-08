import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage.vue'),
    redirect: "/home",
    children: [
      { path: 'home', name: '首页', component: () => import('../views/HomeView.vue')},
      { path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      { path: 'department', name: '部门信息', component: () => import('../views/Department.vue')},
      { path: 'employee', name: '员工信息', component: () => import('../views/Employee.vue')},
      { path: 'customer', name: '客户信息', component: () => import('../views/Customer.vue')},
      { path: 'supplier', name: '供应商信息', component: () => import('../views/Supplier.vue')},
      { path: 'roomOrder', name: '客房预定', component: () => import('../views/RoomOrder.vue')},
      { path: 'room', name: '客房信息', component: () => import('../views/Room.vue')},
      { path: 'cateringOrder', name: '餐饮预定', component: () => import('../views/CateringOrder.vue')},
      { path: 'supplyForm', name: '采购预定', component: () => import('../views/SupplyForm.vue')},
      { path: 'checkInForm', name: '入住记录', component: () => import('../views/CheckInForm.vue')},
      { path: 'materialWastageForm', name: '物资损耗记录', component: () => import('../views/MaterialWastageForm.vue')},
      { path: 'goods', name: '货物信息', component: () => import('../views/Goods.vue')},
      { path: 'warehouseInfo', name: '仓库信息', component: () => import('../views/WarehouseInfo.vue')},
      { path: 'salary', name: '工资信息', component: () => import('../views/Salary.vue')},
      { path: 'bill', name: '账单', component: () => import('../views/Bill.vue')},
      { path: 'financialStatement', name: '财务报表', component: () => import('../views/FinancialStatement.vue')},
    ]
  },
  {
    path: '/login',
    name: '登录',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: '注册',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/404.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


// 提供一个重置路由的方法
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}

router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称
  store.commit("setPath")
  // 如果访问的是登录界面则直接放行
  if (to.path === '/login') return next()
  //获取用户页面token信息
  let token = window.sessionStorage.getItem('token')
  window.sessionStorage.setItem('token',token)
  //如果token数据为null 则跳转到指定路径
  if (!token) return next("/login")
  next()
})

export default router
