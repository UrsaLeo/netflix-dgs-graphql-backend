export const port = process.env.PORT || 4000;
export const graphqlPath = '/graphql';

export const microservices = {
  country: process.env.COUNTRY_URL || 'http://localhost:9000/graphql',
  job: process.env.JOB_URL || 'http://localhost:9001/graphql',
};

export const logging = {
  apollo_logging: process.env.APOLLO_LOGGING == 'true' || false,
  app_logging_level: process.env.APP_LOGGING_LEVEL || 'debug', // info, debug
};
