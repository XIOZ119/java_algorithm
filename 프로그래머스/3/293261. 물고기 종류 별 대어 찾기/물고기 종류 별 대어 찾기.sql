SELECT f.ID, fn.FISH_NAME, f.LENGTH
FROM FISH_INFO f
JOIN FISH_NAME_INFO fn 
ON f.fish_type = fn.fish_type
JOIN (
    SELECT FISH_TYPE, MAX(LENGTH) AS 'LENGTH'
    FROM FISH_INFO
    GROUP BY FISH_TYPE
) m
WHERE m.FISH_TYPE = f.fish_type
AND m.length = f.length
ORDER BY f.id ASC;
