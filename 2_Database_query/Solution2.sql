
SELECT 
    td.user_id,
    u.username,
    td.training_id,
    td.training_date,
    COUNT(td.training_id) AS count
FROM
    Training_details AS td
        JOIN
    user AS u ON u.user_id = td.user_id
GROUP BY td.user_id , td.training_id , td.training_date
HAVING COUNT(td.training_id) > 1
ORDER BY td.training_date DESC;
