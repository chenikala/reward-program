# reward-program
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase

### **Steps to execute on local machine:**
1. Execute ./resources/**data.sql** on H2 database.
2. Launch Postman and import **reward-program.postman_collection.json** file.
3. Run **RewardProgramApplication.java**
4. Check the result on H2 database.

#### Endpoints:
* GET http://localhost:8000/rewardsprogram/api/rewards/summary
* POST http://localhost:8000/rewardsprogram/api/rewards/getrewards
* PUT http://localhost:8000/rewardsprogram/api/rewards/redeem

Swagger URL: http://localhost:8000/rewardsprogram/swagger-ui/index.html