const { GraphQLObjectType, GraphQLInt, GraphQLString, GraphQLList } = require('graphql');

const DepartmentType = new GraphQLObjectType({
  name: 'Department',
  fields: {
    DepartmentID: { type: GraphQLInt },
    Name: { type: GraphQLString },
    GroupName: { type: GraphQLString }
  }
});

module.exports = DepartmentType;