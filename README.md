# FlowPlanner
FlowPlanner

TODO:

Presently, dates in the CSV file must fall before the target date range for a cash flow plan.  Transaction dates that
fall within, or fall after the target date range may be unpredictable.  Some method to treat this should be added.

A means to capture a desired date range should be added for cash flow reports.  Presently, these dates (Start/End) are
hard coded.

Need to ensure error handling for all data inputs needs to be verified and added.

I should probably, move the CashFlowBuilder into the persistence package using a standard DAO.  I'm still contemplating
this.

