import { Outlet, Link } from "react-router-dom";

export const Layout = () => {
    return (
        <div className='container'>
            <header className='d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom'>
                <ul className='nav nav-pills'>
                    <li className='nav-item'><Link to='/' className='nav-link'>Home</Link></li>
                    <li className='nav-item'><Link to='/signup' className='nav-link'>Sign Up</Link></li>
                    <li className='nav-item'><Link to='/login' className='nav-link'>Log In</Link></li>
                    <li className='nav-item'><Link to='/otp' className='nav-link'>OTP</Link></li>
                </ul>
            </header>
            <main>
                <Outlet />
            </main>
        </div>
    );
};