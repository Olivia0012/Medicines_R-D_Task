import * as React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import CssBaseline from '@material-ui/core/CssBaseline';
import useScrollTrigger from '@material-ui/core/useScrollTrigger';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Fab from '@material-ui/core/Fab';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import Zoom from '@material-ui/core/Zoom';
import Alert from '@material-ui/lab/Alert';
import moment from 'moment';

import {
	Grid,
	Table,
	TableHead,
	TableRow,
	TableCell,
	TableBody,
	Button,
	Dialog,
	DialogTitle,
	DialogContent,
	DialogContentText,
	TextField,
	DialogActions,
	Collapse
} from '@material-ui/core';

import { addMedicineRecord, getPastRecord } from './api';

const useStyles = makeStyles((theme) => ({
	root: {
		position: 'fixed',
		bottom: theme.spacing(2),
		right: theme.spacing(2)
	},
	container: {
		display: 'flex',
		flexWrap: 'wrap'
	},
	textField: {
		marginLeft: theme.spacing(1),
		marginRight: theme.spacing(1),
		width: '30%'
	}
}));

function ScrollTop(props) {
	const { children, window } = props;
	const classes = useStyles();

	const trigger = useScrollTrigger({
		target: window ? window() : undefined,
		disableHysteresis: true,
		threshold: 100
	});

	const handleClick = (event) => {
		const anchor = (event.target.ownerDocument || document).querySelector('#back-to-top-anchor');

		if (anchor) {
			anchor.scrollIntoView({ behavior: 'smooth', block: 'center' });
		}
	};

	return (
		<Zoom in={trigger}>
			<div onClick={handleClick} role="presentation" className={classes.root}>
				{children}
			</div>
		</Zoom>
	);
}

ScrollTop.propTypes = {
	children: PropTypes.element.isRequired,
	/**
   * Injected by the documentation to work in an iframe.
   */
	window: PropTypes.func
};

export default function BackToTop(props) {
	const classes = useStyles();

	const [ data, setData ] = React.useState(false);
	const [ open, setDMOpen ] = React.useState(false);
	const [ openAlert, setOpen ] = React.useState(false);
	const [ openGreen, setOpenGreen ] = React.useState(false);
	const [ error, setError ] = React.useState();
	const [ values, setValues ] = React.useState({
		medicine: '',
		amount: 0.0,
		date: moment().format('YYYY-MM-DD HH:mm:ss')
	});

	const handleOpen = () => {
    setOpenGreen(false)
		setDMOpen(true);
	};

	const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
	};

	const handleClose = () => {
    setOpenGreen(false)
		setDMOpen(false);
	};

	const AddNewRecord = async () => {
    
		if (openGreen) {
			window.location.href = './';
		} else {
			const newRecord = {
				id: null,
				medicine: values.medicine,
				amount: values.amount,
				pid: '1',
				takenTime: moment(values.date).format('YYYY-MM-DD HH:mm:ss') + ''
			};
			console.log(newRecord);
			await addMedicineRecord(newRecord)
				.then((response) => {
					console.log(response);
					if (response.data === false) {
						alert('Invalid session,Please login to continue.');
						window.location.href = './';
					}
					setOpenGreen(true);
				})
				.catch((error) => {
					setOpen(true);
					setError(error + '');
				});
		}
	};

	const handleQuery = async () => {
		const pid = '1'; // patient id
		const date = new moment().format('YYYY-MM-DD');
		await getPastRecord(pid, date)
			.then((response) => {
				console.log(response);
				const result = response.data;
				if (result === '') {
					alert('You have not added any record.');
				} else {
					setData(result);
				}
				setOpenGreen(true);
			})
			.catch((error) => {
				setOpen(true);
				setError(error + '');
			});
	};

	return (
		<React.Fragment>
			<CssBaseline />
			<AppBar>
				<Toolbar>
					<Typography variant="h6">MEDICINE RECORDS</Typography>
				</Toolbar>
			</AppBar>
			<Toolbar id="back-to-top-anchor" />
			<Container spacing={10}>
				<Grid container spacing={3} direction="row" justify="flex-end" alignItems="center">
					<Grid item xs={12} >
						<Typography variant="h6"></Typography>
					</Grid>
					<Grid item xs={6}>
						<Button variant="outlined" color="secondary" onClick={(event) => handleQuery(event)}>
							Past Week
						</Button>
            {'   '}
						<Button color="primary" variant="outlined" onClick={(event) => handleOpen(event)}>
							Add
						</Button>
					</Grid>
				</Grid>
				<Collapse in={data ? true : false}>
					<Box my={5} style={{ height: 400, width: '100%' }}>
						<Table>
							<TableHead>
								<TableRow>
									<TableCell>ID</TableCell>
									<TableCell>Medicine</TableCell>
									<TableCell>Amount</TableCell>
									<TableCell>Date</TableCell>
								</TableRow>
							</TableHead>
							<TableBody>
								{data ? (
									data.map((item, index) => (
										<TableRow>
											<TableCell>{index + 1}</TableCell>
											<TableCell>{item.medicine}</TableCell>
											<TableCell>{item.amount}</TableCell>
											<TableCell>{item.takenTime}</TableCell>
										</TableRow>
									))
								) : (
									<div />
								)}
							</TableBody>
						</Table>
					</Box>
				</Collapse>
				<Dialog
					open={open}
					onClose={handleClose}
					aria-labelledby="max-width-dialog-title"
					maxWidth="lg"
					fullWidth
				>
					<DialogTitle id="form-dialog-title">New Record</DialogTitle>
					<DialogContent>
						<Collapse in={!openGreen}>
							<DialogContentText>
								Please fill in the required fileds to add new record.{' '}
							</DialogContentText>
							<TextField
								id="outlined-multiline-flexible"
								required
								fullWidth
								value={values.medicine}
								onChange={handleChange('medicine')}
								label="Medicine Name"
								variant="outlined"
							/>
							<Box p={1} />
							<TextField
								id="outlined-multiline-flexible"
								required
								fullWidth
								value={values.amount}
								onChange={handleChange('amount')}
								label="Taken Amount"
								variant="outlined"
							/>
							<Box p={1} />
							<TextField
								id="datetime-local"
								label="Date"
								type="datetime-local"
								defaultValue={new Date().toLocaleTimeString()}
								className={classes.textField}
								value={values.date}
								onChange={handleChange('date')}
								InputLabelProps={{
									shrink: true
								}}
							/>
						</Collapse>
					</DialogContent>
					<DialogContent>
						<Collapse in={openAlert}>
							<Alert severity="error">{error}</Alert>
						</Collapse>
						<Collapse in={openGreen}>
							<Alert severity="success">Add New Record Successfully!</Alert>
						</Collapse>
					</DialogContent>
					<DialogActions>
						<Collapse in={!openGreen}>
							<Button onClick={handleClose} color="primary">
								Cancel
							</Button>
						</Collapse>
						<Button onClick={AddNewRecord} color="primary" type="submit">
							Confirm
						</Button>
					</DialogActions>
				</Dialog>
			</Container>
			<ScrollTop {...props}>
				<Fab color="secondary" size="small" aria-label="scroll back to top">
					<KeyboardArrowUpIcon />
				</Fab>
			</ScrollTop>
		</React.Fragment>
	);
}
