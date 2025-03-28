# 프로젝트 설정

npx create-react-app [project-name] --template typescript
cd [project-name]
npm install react-router-dom formik yup react-icons@4.3.1
npm start

## Tailwind 설정

npm install -D tailwindcss@3 postcss autoprefixer
npx tailwindcss init -p

### tailwind.config.js

module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [],
};

### stylex.css

@tailwind base;
@tailwind components;
@tailwind utilities;