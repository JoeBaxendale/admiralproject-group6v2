Before starting these tests please follow the instructions in the README file and make sure you have rerun the workbenchRunable.sql in your sql server.

1. Manager Walk-Through

Begin by logging in to the application using the details "karla@hotmail.com" and "password".

You will now see the timesheet dashboard on the url localhost:8080/timesheetDashboard/1/Pending.
On this page you will see a status filter which is set to "Pending", a "Week Ending" filter which is set to "All Dates",
a "Submit Changes" button and three timesheets. 
One will have manager name of "Peter Bridge", a contractor name of "Sara Beckket" and a status of Pending.

Click on this timesheet and you will see another set of information containing "Number Of Days Worked", "Overtime Worked",
"Start Date", "End Date", "Date Submitted", "Notes" and "Status"
The row below will contain "2","0","2019-11-13","2019-11-15","2019-11-18","" and a drop down box set to "Pending" in the respective columns.

Click on the drop down box and three options will appear; "Pending", "Rejected" and "Approved".
Select "Approved".
Click on "Submit Changes".

The page will reload with a fade out and then back in and the timesheet that you altered will no longer be listed on the dashboard.

Click on the status filter and you will see the same three options will appear; "Pending", "Rejected", "Approved".
Select "Approved".

The page will reload with a fade out and then back in and the timesheets that are on the page will have changed.
The url will also have changed to "localhost:8080/timesheetDashboard/1/Approved.

You will see that the timesheet we changed is now the first entry on this page and you can confirm this by clicking the timesheet for the extra
information and identifying that it is identical.

Logout using the button under the "karla@hotmail.com" drop down.

2. Date Filter

For this test we have to have some prior steps fulfulled.
This is because the test is date dependant and so we have to add a timesheet with a valid date to make sure that this test works correctly.

To begin with, login using "daniel@hotmail.com" and "password".

You will see the timesheet form which says "Hello Daniel@Hotmail.Com Please Fill In Your Time Sheet".

Insert 6 into the number of days input

Click the worked saturday checkbox

Insert 2 into the over time input

Click on the start date picker and pick the monday at the start of your current week. Then go to the end date picker and pick the saturday.

Click the "Submit" button.

If any errors are thrown, follow the on screen instructions and resubmit.

Click on daniel@hotmail.com in the top right corner.

Login using "karla@hotmail.com" and "password".

Click on the date filter and select the top filter.

You will see that all timesheets that were not in that week will disappear and the timesheet you just submitted remains if the date is correct.

If the date was incorrectly entered, please check the other filter options.