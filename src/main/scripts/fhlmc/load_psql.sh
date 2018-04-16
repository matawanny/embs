
if [ -z "$1" ]; then
  echo "Usage: $0 <host> <user>"
  exit
fi

if [ -z "$2" ]; then
  echo "Usage: $0 <host> <user>"
  exit
fi

host=$1
user=$2

# load the movies table
psql -U $user -h $host -c "create table movies (movie_id int, title varchar, genres varchar);"
cat movies.csv | psql -U $user -h $host -c "copy movies from stdin with delimiter ',' csv header;"

# load the ratings table
# this uses the files created by format_ratings.sh
psql -U $user -h $host -c "create table ratings (user_id int, movie_id int, rating float4, timestamp bigint);"
cat ratings_1.csv | psql -U $user -h $host -c "copy ratings from stdin with delimiter ',' csv header;"
cat ratings_2.csv | psql -U $user -h $host -c "copy ratings from stdin with delimiter ',' csv header;"
