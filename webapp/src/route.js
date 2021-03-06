import React from 'react';

const SignUp1 = React.lazy(() => import('./components/Authentication/SignUp1'));
const Signin1 = React.lazy(() => import('./components/Authentication/SignIn1'));
const Login = React.lazy(() => import('./views/Login'));

const route = [
    { path: '/auth/signup-1', exact: true, name: 'Signup 1', component: SignUp1 },
    { path: '/auth/signin-1', exact: true, name: 'Signin 1', component: Signin1 },
    { path: '/login', exact: true, name: 'Login', component: Login },
];

export default route;