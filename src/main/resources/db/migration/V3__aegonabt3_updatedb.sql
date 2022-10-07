-- update emergency
ALTER TABLE emergency add column Name varchar(50),
						add column DateCreated date;

-- drop column snippet
ALTER TABLE snippet
    drop column Url,
	drop column FileOwnerId,
	drop column Published;