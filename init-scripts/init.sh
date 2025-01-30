#!/bin/bash

# Czekaj na uruchomienie Cassandra
while ! nodetool status 2>/dev/null | grep -q "UN"; do
  echo "Waiting for Cassandra..."
  sleep 5
done

# Wykonaj skrypt CQL
cqlsh -e "SOURCE /docker-entrypoint-initdb.d/schema.cql"