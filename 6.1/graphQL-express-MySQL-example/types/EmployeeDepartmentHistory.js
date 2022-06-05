const { GraphQLObjectType, GraphQLInt, GraphQLString, GraphQLList } = require('graphql');
const { dbQuery } = require('../database');
const DepartmentType = require('./Department');

const EmployeeDepartmentHistory = new GraphQLObjectType({
  name: 'EmployeeDepartmentHistory',
  fields: {
    EmployeeID: { type: GraphQLInt },
    ShiftID: { type: GraphQLInt },
    Department: { 
        type: DepartmentType,
        resolve: async (department) => {
          let res = await dbQuery(`SELECT * FROM Department WHERE DepartmentID = ${parseInt(department.DepartmentID)}`);
          return res[0];
        }
      }
  }
});

module.exports = EmployeeDepartmentHistory;