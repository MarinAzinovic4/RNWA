const { GraphQLObjectType } = require('graphql');
const insertUser = require('./insertUser');
const insertDepartment = require('./insertDepartment');

const Mutation = new GraphQLObjectType({
  name: 'mutation',
  fields: {
    // Insert a new user record
    insertUser: insertUser,
    insertDepartment: insertDepartment
  }
});

module.exports = Mutation;
