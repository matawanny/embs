 compare FHLMC ARM loan
1. Create fhlmc_missing cusip table
drop view prd.fhlmc_missing_cusips_view_ARM_2018_04;
create view if not exists prd.fhlmc_missing_cusips_view_ARM_2018_04 as
select t1.cusip from
(select distinct p.cusip  from prd.fhlmc_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where  f.prod_type_ind = 'A' and f.current_upb>0 
) t1 
left outer join(
select distinct cusip from prd.fhlmonly 
where year=2018 and month=4 and amortization_type='ARM'
) t2
on(t1.cusip=t2.cusip)
where t2.cusip is null

2. Create View from Hive table

drop view prd.fhlmonly_view_ARM_2018_4;
create view prd.fhlmonly_view_ARM_2018_4 as
select substr(loan_identifier, 8) as loan_num,cusip,mortgage_loan_amount,
issuance_investor_loan_upb as orig_upb, current_investor_loan_upb as current_upb,
amortization_type as amort_type, original_interest_rate as orig_note_rate,
issuance_interest_rate as pc_issuance_note_rate,
loan_term as orig_loan_term, remaining_months_to_maturity as rem_months_to_maturity,
loan_age,interest_only_loan_indicator as io_flag,
interest_only_first_principal_and_interest_payment_date as first_pi_date,
months_to_amortization as months_to_amortize
from prd.fhlmonly where year=2018 and month=4 and amortization_type='ARM';

3. Create View from Sqoop Table

drop view prd.fhlmc_loan_sqoop_ARM_view;
create view prd.fhlmc_loan_sqoop_ARM_view as
select f.loan_seq_num as loan_num,p.cusip, f.orig_loan_amt as mortgage_loan_amount,
f.orig_upb,f.current_upb,concat(f.prod_type_ind,'','RM') as amort_type,
f.orig_note_rate,f.pc_issuance_note_rate, f.orig_loan_term,
f.rem_months_to_maturity,f.loan_age,f.io_flag,first_pi_date,
if(f.io_flag='N',null, f.months_to_amortize) as months_to_amortize
from prd.fhlmc_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id=p.product_id)
where  f.prod_type_ind = 'A' and f.current_upb>0 
and f.eff_date >=unix_timestamp('2018-04-01','yyyy-MM-dd')
and p.cusip not in (select cusip from fhlmc_missing_cusips_view_arm_2018_04)

4. Do comparation

select * from prd.fhlmc_loan_sqoop_ARM_view a 
full outer join prd.fhlmonly_view_ARM_2018_4 b 
on a.cusip=b.cusip and a.loan_num=cast(b.loan_num as int)
and round(a.mortgage_loan_amount)=round(b.mortgage_loan_amount)
and round(a.orig_upb,3)=round(b.orig_upb,3)
and round(a.current_upb,3)=round(b.current_upb,3)
and round(a.orig_note_rate,3)=round(b.orig_note_rate,3)
and round(a.pc_issuance_note_rate,3)=round(b.orig_note_rate,3)
and a.orig_loan_term=b.orig_loan_term
and a.rem_months_to_maturity=b.rem_months_to_maturity
and a.loan_age=b.loan_age
and a.io_flag=b.io_flag 
and a.rem_months_to_maturity=b.rem_months_to_maturity
and nvl(a.first_pi_date,0)=nvl(b.first_pi_date,0)
where
a.loan_num is null or b.loan_num is null

 compare FHLMC FRM loan
1. Create fhlmc_missing cusip table
drop view prd.fhlmc_missing_cusips_view_FRM_2018_04;
create view if not exists prd.fhlmc_missing_cusips_view_FRM_2018_04 as
select t1.cusip from
(select distinct p.cusip  from prd.fhlmc_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where  f.prod_type_ind = 'F' and f.current_upb>0 
) t1 
left outer join(
select distinct cusip from prd.fhlmonly 
where year=2018 and month=4 and amortization_type='FRM'
) t2
on(t1.cusip=t2.cusip)
where t2.cusip is null

2. Create View from Hive table

drop view prd.fhlmonly_view_FRM_2018_4;
create view prd.fhlmonly_view_FRM_2018_4 as
select substr(loan_identifier, 8) as loan_num,cusip,mortgage_loan_amount,
issuance_investor_loan_upb as orig_upb, current_investor_loan_upb as current_upb,
amortization_type as amort_type, original_interest_rate as orig_note_rate,
issuance_interest_rate as pc_issuance_note_rate,
loan_term as orig_loan_term, remaining_months_to_maturity as rem_months_to_maturity,
loan_age,interest_only_loan_indicator as io_flag,
interest_only_first_principal_and_interest_payment_date as first_pi_date,
months_to_amortization as months_to_amortize,
modification_program as mod_prog,modification_type as mod_type,
number_of_modifications as num_of_mods,
loan_age_as_of_modification as age_asof_mod, days_delinquent as months_delinq,
loan_performance_history as loan_perf_hist 
from prd.fhlmonly where year=2018 and month=4 and amortization_type='FRM';

3. Create View from Sqoop Table
drop view prd.fhlmc_loan_sqoop_FRM_view;
create view prd.fhlmc_loan_sqoop_FRM_view as
select f.loan_seq_num as loan_num,p.cusip, f.orig_loan_amt as mortgage_loan_amount, 
f.orig_upb,f.current_upb,concat(f.prod_type_ind,'','RM') as amort_type,
f.orig_note_rate,f.pc_issuance_note_rate, f.orig_loan_term,
f.rem_months_to_maturity,f.loan_age,f.io_flag,f.first_pi_date,
if(f.io_flag='N',null, f.months_to_amortize) as months_to_amortize,
m.mod_program as mod_prog, m.mod_type,m.num_of_mods,
m.mod_date_loan_age as age_asof_mod
from prd.fhlmc_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id=p.product_id)
left outer join prd.fhlmc_mod_loan_sqoop m on (f.product_id=m.product_id 
and f.loan_seq_num=m.loan_seq_num)
where  f.prod_type_ind = 'F' and f.current_upb>0 
and f.last_chg_date >=unix_timestamp('2018-04-01','yyyy-MM-dd')*1000

and p.cusip not in (select cusip from fhlmc_missing_cusips_view_FRM_2018_04)

4. Do the comparation
select * from prd.fhlmc_loan_sqoop_FRM_view a 
full outer join prd.fhlmonly_view_FRM_2018_4 b 
on a.cusip=b.cusip and a.loan_num=cast(b.loan_num as int)
and round(a.mortgage_loan_amount)=round(b.mortgage_loan_amount)
and round(a.orig_upb,3)=round(b.orig_upb,3)
and round(a.current_upb,3)=round(b.current_upb,3)
and round(a.orig_note_rate,3)=round(b.orig_note_rate,3)
and round(a.pc_issuance_note_rate,3)=round(b.orig_note_rate,3)
and a.orig_loan_term=b.orig_loan_term
and a.rem_months_to_maturity=b.rem_months_to_maturity
and a.loan_age=b.loan_age
and a.io_flag=b.io_flag 
and a.rem_months_to_maturity=b.rem_months_to_maturity
and nvl(a.first_pi_date,0)=nvl(b.first_pi_date,0)
and nvl(a.mod_prog,'')=nvl(b.mod_prog,'')
and nvl(a.mod_type,'')=nvl(b.mod_type,'')
and nvl(a.num_of_mods,0)=nvl(b.num_of_mods,0)
and nvl(a.age_asof_mod,0)=nvl(b.age_asof_mod,0)
where
a.loan_num is null or b.loan_num is null

