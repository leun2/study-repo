import React from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { FaGoogle, FaFacebook } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  
console.log(FaGoogle);

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-100 p-4">
      <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-lg">
        <h2 className="text-center text-2xl font-bold text-gray-800">로그인</h2>
        
        {/* Formik 사용 */}
        <Formik
          initialValues={{ email: "", password: "" }}
          validationSchema={Yup.object({
            email: Yup.string().email("유효한 이메일을 입력하세요").required("필수 입력"),
            password: Yup.string().min(6, "6자 이상 입력하세요").required("필수 입력"),
          })}
          onSubmit={(values) => {
            console.log("로그인 정보:", values);
            alert("로그인 성공!");
            navigate("/dashboard");
          }}
        >
          {({ isSubmitting }) => (
            <Form className="mt-6">
              {/* 이메일 입력 */}
              <div className="mb-4">
                <label className="block text-gray-600">이메일</label>
                <Field
                  type="email"
                  name="email"
                  className="w-full p-2 mt-1 border rounded-lg"
                  placeholder="example@email.com"
                />
                <ErrorMessage name="email" component="div" className="text-red-500 text-sm" />
              </div>

              {/* 비밀번호 입력 */}
              <div className="mb-4">
                <label className="block text-gray-600">비밀번호</label>
                <Field
                  type="password"
                  name="password"
                  className="w-full p-2 mt-1 border rounded-lg"
                  placeholder="******"
                />
                <ErrorMessage name="password" component="div" className="text-red-500 text-sm" />
              </div>

              {/* 로그인 버튼 */}
              <button
                type="submit"
                disabled={isSubmitting}
                className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition"
              >
                로그인
              </button>
            </Form>
          )}
        </Formik>

        {/* 소셜 로그인 */}
        <div className="mt-6">
            <p className="text-center text-gray-500">또는</p>
            <div className="flex flex-col justify-center mt-4 space-y-4">
                <button className="flex items-center px-4 py-3 bg-red-500 text-white rounded-lg hover:bg-red-600 w-full">
                    <FaGoogle className="w-5 h-5 mr-3" /> 
                    <span className="flex-1 text-center">Google 로그인</span>
                </button>
                <button className="flex items-center justify-center px-4 py-3 bg-blue-700 text-white rounded-lg hover:bg-blue-800 w-full">
                    <FaFacebook className="w-5 h-5 mr-3" /> 
                    <span>Facebook 로그인</span>
                </button>
            </div>
        </div>

        {/* 하단 링크 */}
        <div className="mt-6 text-center">
          <p className="text-gray-600">
            계정이 없나요? <a href="/signup" className="text-blue-500">회원가입</a>
          </p>
          <p className="text-gray-600">
            비밀번호를 잊으셨나요? <a href="/forgot-password" className="text-blue-500">비밀번호 찾기</a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;
