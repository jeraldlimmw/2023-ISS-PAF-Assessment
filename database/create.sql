-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files

-- create db
create database brewery;

-- load SQL files
use brewery;

source /Users/jeraldlim/tfip/assessment_template/database/beers.sql;

source /Users/jeraldlim/tfip/assessment_template/database/breweries.sql;

source /Users/jeraldlim/tfip/assessment_template/database/categories.sql;

source /Users/jeraldlim/tfip/assessment_template/database/geocodes.sql;

source /Users/jeraldlim/tfip/assessment_template/database/styles.sql;