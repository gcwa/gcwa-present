#!/bin/bash
#Generate All .CDX Indexes for all files in the folder supplied in the first argument
ARCHIVE_BASE_DIR=$1;
TARGET_MERGED_FILE=$2;

declare -i COUNTER
COUNTER=0

for file in $ARCHIVE_BASE_DIR*
do
	
	let "COUNTER+=1"
		
	echo "PROCESSING $file"
	echo "COUNTER = $COUNTER"

	/home/webarch/local/openwayback/bin/./cdx-indexer $file index$COUNTER.cdx;
	chmod -R 0755 index$COUNTER.cdx;	
	
done
	
#Now sort and merge all files

	LC_ALL=C sort -m -u -o all.cdx index*.cdx;
	rm -rf index*.cdx;
	LC_ALL=C sort -u all.cdx > $TARGET_MERGED_FILE;
	rm -rf all.cdx;
	chmod -R 0755 $TARGET_MERGED_FILE;



