import React from 'react';
import Patient from './Patient'

const routes = [
	{
		path: '/',
		element: <Patient />,
		children: [ { path: '', element: <Patient /> } ]
	}
];

export default routes;