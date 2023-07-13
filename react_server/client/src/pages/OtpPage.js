import { useState } from 'react';
import axios from 'axios';

export const OtpPage = () => {
    const [username, setUsername] = useState('');
    const [otp, setOtp] = useState('');
    async function onSubmit(e) {
        e.preventDefault();
        const response = axios.post('/user-server/api/otp', {
            username,
            code: otp,
        });
        console.info(response.data);
    }
    return (
        <div className='row'>
            <div className='col-sm-12'>
                <h1>Otp</h1>
                <form onSubmit={onSubmit}>
                    <div className='row g-3'>
                        <div className='col-12'>
                            <input onChange={e => setUsername(e.target.value)} type='text' className='form-control' placeholder='username' autoFocus></input>
                        </div>
                        <div className='col-12'>
                            <input onChange={e => setOtp(e.target.value)} type='text' className='form-control' placeholder='OTP'></input>
                        </div>
                        <div className='col-12'>
                            <button type='submit' className='form-control btn btn-primary'>Sign Up</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
};