SELECT CONCAT(f.length, 'cm') AS 'MAX_LENGTH'
FROM fish_info f
ORDER BY f.length DESC
LIMIT 1;