#!/bin/sh

pg_dump --table=warehouse --data-only --column-inserts pivotal_test > ./src/test/resources/data.sql -U postgres