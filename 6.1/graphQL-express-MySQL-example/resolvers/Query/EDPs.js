const { GraphQLList } = require('graphql');
const { dbQuery } = require('../../database');
const { EmployeeDepartmentHistoryType } = require('../../types');

const fieldsEmployeeDepartmentHistory = {
  type: new GraphQLList(EmployeeDepartmentHistoryType),
  async resolve(_, {}){
    let res = await dbQuery(`SELECT * FROM EmployeeDepartmentHistory`);
    return res;
  }
};

module.exports = fieldsEmployeeDepartmentHistory;
