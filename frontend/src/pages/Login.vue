<template>
  <div class="body">
    <div class="container">
      <h2>로그인</h2>
      <form @submit.prevent="submit" id="loginForm">
        <div class="input-group">
          <label for="email">이메일</label>
          <input
              type="email"
              placeholder="이메일을 입력하세요"
              id="email"
              v-model="state.form.email"
              @blur="validateEmail"
              required
          />
          <small v-if="emailError" style="color: red;">* 올바른 이메일 주소를 입력해주세요</small>
        </div>
        <div class="input-group">
          <label for="password">비밀번호</label>
          <input
              type="password"
              placeholder="비밀번호를 입력하세요"
              id="password"
              v-model="state.form.password"
              @blur="validatePassword"
              required
          />
          <small v-if="passwordError" style="color: red;">
            * 비밀번호는 8자 이상 20자 이하이며 대문자, 소문자, 숫자, 특수문자를 각각 최소 1개 포함해야 합니다.
          </small>
        </div>
        <button type="submit" :disabled="!isFormValid">로그인</button>
      </form>
      <p>
        <a href="./register.html" class="signup-link">회원가입</a>
      </p>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        email: "",
        password: ""
      }
    });

    const emailError = ref(false);
    const passwordError = ref(false);

    const validateEmail = () => {
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      emailError.value = !emailPattern.test(state.form.email);
    };

    const validatePassword = () => {
      const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,20}$/;
      passwordError.value = !passwordPattern.test(state.form.password);
    };

    const isFormValid = () => {
      return !emailError.value && !passwordError.value && state.form.email && state.form.password;
    };

    const submit = () => {
      axios.post("/api/account/login", state.form).then((res) => {
        store.commit('setAccount', res.data);
        sessionStorage.setItem("id", res.data.id);
        sessionStorage.setItem("name", res.data.name);
        router.push({ path: "/home" });
        window.alert(`${res.data.name}님 환영합니다.`);
      }).catch(() => {
        window.alert("로그인 정보가 존재하지 않습니다.");
      });
    };

    return { state, emailError, passwordError, validateEmail, validatePassword, isFormValid, submit };
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@700&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap");

.body {
  background-color: #2c3e50; /* 배경색 설정 */
  height: 100vh; /* 전체 높이 설정 */
  display: flex; /* 플렉스 박스 설정 */
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
}

.container {
  font-family: Arial, sans-serif; /* 폰트 스타일 설정 */
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 400px; /* 고정된 너비 */
  text-align: center; /* 텍스트 중앙 정렬 */
}

h2 {
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="email"],
input[type="password"] {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%; /* 부모 요소의 너비에 맞추기 */
  box-sizing: border-box; /* 패딩과 테두리를 포함하여 전체 너비 계산 */
}

button {
  width: 100%;
  padding: 10px;
  background-color: #979FD8;;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px; /* 버튼 위 여백 추가 */
}

button:hover {
  background-color: #2c3e50;
}

.signup-link {
  color: gray;
  font-size: 14px;
  text-decoration: none;
}

.signup-link:hover {
  color: black; /* 호버 시 진한 회색으로 변경 */
}
</style>


