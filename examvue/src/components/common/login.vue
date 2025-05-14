<!-- 用户登录界面 -->
<template>
  <div id="login">
    <div class="bg"></div>
    <el-row class="main-container">
      <el-col :lg="8" :xs="16" :md="10" :span="10">
        <div class="top">
          <i class="iconfont icon-kaoshi"></i><span class="title">ncwu在线考试系统</span>
        </div>
        <div class="bottom">
          <div class="container">
            <p class="title">用户登录</p>
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="账号">
                <el-input v-model.number="formLabelAlign.username" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="formLabelAlign.password" placeholder="请输入密码" type='password'></el-input>
              </el-form-item>
              <div class="submit">
                <el-button type="primary" class="row-login" @click="login()">登录</el-button>
              </div>
              <!-- <div class="options">
                <p class="find"><a href="javascript:;">找回密码</a></p>
                <div class="register">
                  <span>没有账号?</span>
                  <span><a href="javascript:;">去注册</a></span>
                </div>
              </div> -->
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="footer">
      <el-col>
        <p class="msg2">ncwu在线考试系统</p>
      </el-col>
    </el-row>
    <section class="remind">
      <span>超级管理员账号：9999</span>
      <span>密码是：123456</span>
    </section>
    
  </div>
</template>

<script>
import store from '@/vuex/store'
import {mapState} from 'vuex'
export default {
  store,
  name: "login",
  data() {
    return {
      role: 2,
      labelPosition: 'left',
      formLabelAlign: {
        username: '202126729',
        password: '123456'
      }
    }
  },
  methods: {
    //用户登录请求后台处理
    login() {
      console.log("登录操作执行-------");
      this.$axios({
        url: `/api/login`,
        method: 'post',
        data: {
          ...this.formLabelAlign
        }
      }).then(res=>{
        let resData = res.data.data
        if(resData != null) {
          switch(resData.role) {
            case "0":  //管理员
              this.$cookies.set("cname", resData.adminName)
              this.$cookies.set("cid", resData.adminId)
              this.$cookies.set("role", 0)
              this.$cookies.set("csex", resData.sex)//11
              this.$cookies.set("ctel", resData.tel)
             
              this.$router.push({path: '/index' }) //跳转到首页
              break
            case "1": //教师
              this.$cookies.set("cname", resData.teacherName)
              this.$cookies.set("cid", resData.teacherId)
              this.$cookies.set("role", 1)
              this.$router.push({path: '/index' }) //跳转到教师用户
              break
            case "2": //学生
              this.$cookies.set("cname", resData.studentName)
              this.$cookies.set("cid", resData.studentId)
              this.$cookies.set("ctel", resData.tel)
              this.$cookies.set("cgrade", resData.grade)
              this.$cookies.set("cemail", resData.email)
              this.$cookies.set("cpwd", resData.pwd)
              this.$cookies.set("cmajor", resData.major)
              this.$cookies.set("csex", resData.sex)
              this.$router.push({path: '/student'})
             
              break
          }
        }
        if(resData == null) { //错误提示
          this.$message({
            showClose: true,
            type: 'error',
            message: '用户名或者密码错误'
          })
        }
      })
    },
    clickTag(key) {
      this.role = key
    }
  },
  computed: mapState(["userInfo"]),
  mounted() {

  }
}
</script>

<style lang="scss" scoped>
.remind {
  border-radius: 15px;
  padding: 10px 20px;
  display: flex;
  position: fixed;
  right: 200px;
  bottom: 60%;
  flex-direction: column;
  color: #ff6100;
  background-color: #fff;
  border-left: 0px solid #409eff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)
}
.container {
  margin-bottom: 32px;
}
.container .el-radio-group {
  margin: 30px 0px;
}
a:link {
  color:#ff962a;
  text-decoration:none;
}
#login {
  font-size: 7px;
  color: #000;
  background-color: #fff;
}
#login .bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  overflow-y: auto;
  height: 100%;
  background: url('../../assets/img/loginbg.png')center top / cover no-repeat;
  background-color: #fff !important;
}
#login .main-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
#login .main-container .top {
  margin-top: 120px;
  font-size: 30px;
  color: #ff222a;
  display: flex;
  justify-content: center;
}
#login .top .icon-kaoshi {
  font-size: 0px;
}
#login .top .title {
  margin-top: 20px;
  font-size: 50px;
}
#login .bottom {
  display:flex;
  justify-content: center;
  background-color:#ff0;
  border-radius: 5px;
  width: 100%;
  height: 100%;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
#login .bottom .title {
  text-align: center;
  font-size: 50px;
}
.bottom .container .title {
  margin: 30px 0px;;
}
.bottom .submit .row-login {
  width: 100%;
  background-color: #04468b;
  border-color: #04468b;
  margin: 20px 0px 10px 0px;
  padding: 15px 20px;
}
.bottom .submit {
  display: flex;
  justify-content: center;
}
.footer {
  margin-top: 50px;
  text-align: center;
}
.footer .msg1 {
  font-size: 18px;
  color: #fff;
  margin-bottom: 15px;
}
.footer .msg2 {
  font-size: 16px;
  color: #ff222a;
  margin-top: 70px;
}
.bottom .options {
  margin-bottom: 40px;
  color: #ff962a;
  display: flex;
  justify-content: space-between;
}
.bottom .options > a {
  color: #ff962a;
}
.bottom .options .register span:nth-child(1) {
  color: #8C8C8C;
}
</style>
