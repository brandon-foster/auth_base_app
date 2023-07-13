import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Layout } from './layout/Layout';
import { HomePage } from './pages/HomePage';
import { SignUpPage } from './pages/SignUpPage';
import { LogInPage } from './pages/LogInPage';
import { OtpPage } from './pages/OtpPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' exact element={<Layout/>}>
          <Route index exact element={<HomePage/>}></Route>
          <Route path='/signup' exact element={<SignUpPage/>}></Route>
          <Route path='/login' exact element={<LogInPage/>}></Route>
          <Route path='/otp' exact element={<OtpPage/>}></Route>
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
