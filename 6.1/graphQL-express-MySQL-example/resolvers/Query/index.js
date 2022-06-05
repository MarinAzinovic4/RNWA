const { GraphQLObjectType } = require('graphql');

const fieldsUser = require('./user');
const fieldsUsers = require('./users');
const fieldsPosts = require('./posts');
const fieldsDepartments = require('./departments');
const EDPs = require('./EDPs');

const Query = new GraphQLObjectType({
  name: 'Query',
  fields: {
    // Query one user
    user: fieldsUser,
    // Query all users
    users: fieldsUsers,
    // Query all posts
    posts: fieldsPosts,
    
    departments: fieldsDepartments,

    EDPS: EDPs
  }
});

module.exports = Query;