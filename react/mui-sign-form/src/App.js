import React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Header from './Header';
import SignUpForm from './SignUpForm';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

function App() {
  return (
    <div>
        <CssBaseline />
        <Header />
        <SignUpForm />
      </div>
  );
}

export default App;
