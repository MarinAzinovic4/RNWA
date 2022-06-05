const { GraphQLString } = require('graphql');
const { dbQuery } = require('../../database');
const { DepartmentType } = require('../../types');

const insertDepartment = {
  type: DepartmentType,
  args: {
    Name: { type: GraphQLString },
    GroupName: { type: GraphQLString }
  },
  async resolve(_, { Name, GroupName }){
    let res = await dbQuery(`INSERT INTO Department (Name, GroupName) values ('${Name}', '${GroupName}')`);
    return { id: res.insertId, Name, GroupName }
  }
};

module.exports = insertDepartment;