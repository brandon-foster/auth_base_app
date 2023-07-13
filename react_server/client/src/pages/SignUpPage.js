import { useState } from 'react';
import axios from 'axios';

export const SignUpPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    async function onSubmit(e) {
        e.preventDefault();
        const response = axios.post('/api/signup', {
            username,
            password,
            confirmPassword,
        });
        console.info(response.data);
    }
    return (
        <div className='row'>
            <div className='col-sm-12'>
                <h1>Sign Up</h1>
                <form onSubmit={onSubmit}>
                    <div className='row g-3'>
                        <div className='col-12'>
                            <input onChange={e => setUsername(e.target.value)} type='text' className='form-control' placeholder='username' autoFocus></input>
                        </div>
                        <div className='col-12'>
                            <input onChange={e => setPassword(e.target.value)} type='password' className='form-control' placeholder='password'></input>
                        </div>
                        <div className='col-12'>
                            <input onChange={e => setConfirmPassword(e.target.value)} type='password' className='form-control' placeholder='confirm password'></input>
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