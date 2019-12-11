These are some of the manual test walkthroughs
Login:
	- Incorrect/Blank Username: In each case the error message of "Email or Password is invalid" will appear.
	- Incorrect/Blank Password: In each case the error message of "Email or Password is invalid" will appear.
	- Correct Admin Username and password: Routes to the timesheetDashboard/3/Approved url correctly.
	- Correct Manager Username and password: Routes to the /timesheetDashboard/1/Pending url correctly.
	- Correct Contractor Username and password: Routes to the /Timesheet url correctly.

Login Remember me:
	-Incorrect/Blank Username With Remember me: In each case the error message of "Email or Password is invalid" no cookie is created
	-Incorrect/Blank Password With Remember me: In each case the error message of "Email or Password is invalid" no cookie is created
	-Correct Admin Username and password With Remember me: Routes to the timesheetDashboard/3/Approved url correctly. 
	-
