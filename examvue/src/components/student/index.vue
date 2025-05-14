<!--学生考试首页-->
<template>
  <div id="student">
    <el-row class="padding-50">
      <el-col :span="24">
        <ul class="list">
          <li class="logo"><i class=""></i><span>考试中心</span></li>
          <li><a  @click="exam()">我的试卷</a></li>
          <li><router-link to="/scoreTable">我的分数</router-link></li>
          <li><router-link to="/message">留言</router-link></li>
          
          <li class="right" @mouseenter="flag = !flag" @mouseleave="flag = !flag">
            <a href="javascript:;"><i class="iconfont icon-Userselect icon"></i>{{user.userName}}</a>
            <div class="msg" v-if="flag">
              <p @click="manage()">管理中心</p>
              <p class="exit" @click="exit()">退出</p>
            </div>
          </li>
        </ul>
      </el-col>
    </el-row>
    <!--路由区域-->
    <div class="main">
      <router-view></router-view>
    </div>
    <v-footer></v-footer>
  </div>
</template>

<script>
import myFooter from "@/components/student/myFooter"
import store from '@/vuex/store'
import {mapState} from 'vuex'
export default {
  store,
  components: {
    "v-footer": myFooter
  },
  data() {
    return {
      flag: false,
      user: {}
    }
  },
  created() {
    this.userInfo()
  },
  methods: {
    exit() {  //退出登录
      this.$router.push({path:"/"}) //跳转到登录页面
      this.$cookies.remove("cname") //清除cookie
      this.$cookies.remove("cid")
      
    },
    manage() {  //跳转到修改密码页面
      this.$router.push({path: '/manager'})
    },
    userInfo() {
      let studentName = this.$cookies.get("cname")
      let studentId = this.$cookies.get("cid")
      let tel = this.$cookies.get("ctel")
      let grade = this.$cookies.get("cgrade")
      let email = this.$cookies.get("cemail")
      let pwd = this.$cookies.get("cpwd")
      let major = this.$cookies.get("cmajor")
      console.log(`studentId${studentId}`)
      console.log(`studentName ${studentName}`)
      console.log(`tel ${tel}`)
      console.log(`email ${email}`)
      console.log(`grade ${grade}`)
      console.log(`pwd ${pwd}`)
      console.log(`major ${major}`)
      this.user.userName = studentName
      this.user.studentId = studentId
      this.user.tel=tel
      this.user.grade=grade
      this.user.email=email
      this.user.major=major

    },
    practice() { //跳转练习模式
      let isPractice = true
      this.$store.commit("practice", isPractice)
      this.$router.push({path:'/startExam'})
    },
    exam() { //跳转考试模式
     let isPractice = false
      this.$store.commit("practice", isPractice)
      this.$router.push({path:'/student'})
    }
  },
  computed:mapState(["isPractice"])
}
</script>

<style scoped>
.right .icon {
  margin-right: 6px;
}
#student .padding-50 {
  margin: 0 auto;
  padding: 0 50px;
  box-shadow: 0 0 10px 4px rgba(255, 73, 1, 0.1);
  background-color: #13caef;
}
.list a {
  text-decoration: none;
  color: #334046;
}
li {
  list-style: none;
  height: 60px;
  line-height: 60px;
}
#student .list{
  display: flex;
}
#student .list li {
  padding: 0 20px;
  cursor: pointer;
}
/* #student .list li:hover {
  background-color: #0195ff;
  transition: all 2s ease;
} */
/* #student .list li:hover a {
  color: #fff;
} */
#student .list .right {
  margin-left: auto;
  position: relative;
}
#student .list li.right :hover a {
  color: #000;
}
#student .list .logo {
  display: flex;
  font-weight: bold;
  color: #010c16;
}
#student .list .logo i {
  font-size: 50px;
}
.right .msg {
  text-align: center;
  position: absolute;
  top: 60px;
  left: 0px;
  display: flex;
  flex-direction: column;
  border-radius: 2px;
  border-bottom: 3px solid #ffffff;
  background-color: #fff;
}
.right .msg p {
  height: 40px;
  line-height: 40px;
  width: 127px;
}
/* .right .msg p:hover {
  background-color: #0195ff;
} */
</style>
