# FlowPlanner


**TODO & Notes:**

* Presently, dates in the CSV file must fall before or within the target date range for a cash flow plan.  If the 
transaction falls after the specified range, it may be unpredictable.  Some method to treat this should be added.

* Need to ensure error handling for all data inputs needs to be verified and added.  Date entry for example is quite 
vulnerable.

* The current pay period break point is a transaction type 3.  There's likely a better way to do this.  This, however, 
was simple and got the job done.  I should consider implementing a better way.  Blank space separation gets applied 
right before this transaction type (3).   

* Transaction Edit, option #1 was not implemented.  Was simply too easy to edit the CSV directly.  I'll revisit this.

* Note, all date formats are presently:  yyyy-MM-dd

* Need to add UI to capture output drive for the cash flow plan.  

* Right now, so to avoid transactions landing on and splitting up the pay periods (assuming multiple and concurrent 
pay periods), I added the amount field.  by doing this, I was able to add an && to the weekend date adjustment so 
Thursday and Friday would also need to be a negative number (therefore not income) to be set back to the previous 
Wednesday.

* As of the latest update.  I killed all export and edits via the application.  These things would be handled within the CSV file for now. 

