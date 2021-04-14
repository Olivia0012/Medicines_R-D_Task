const axios = require('axios');
axios.defaults.withCredentials = true;

// add new medicine record
export async function addMedicineRecord(medicineRecord) {
	const endpoint = '/api/medicine'; //subjectId=`+subjectId;
	const dataFetched = await axios({
		url: endpoint, 
		method: 'POST',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		},
		data: JSON.stringify(medicineRecord)
	});
	console.log(dataFetched);
	return dataFetched;
}


export async function getPastRecord(pid,date) {
	const endpoint = '/api/medicine?pid='+pid+'&date='+date; 
	const dataFetched = await axios({
		url: endpoint, // send a request to the library API
		method: 'GET',
		headers: {
			withCredentials: true,
			'Content-Type': 'application/json',
			token: localStorage.getItem('token')
		}
	});
	console.log(dataFetched);
	return dataFetched;
}