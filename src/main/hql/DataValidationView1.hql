 compare FNMA ARM loan
1. Create fnma_missing cusip table
drop view prd.fnma_missing_cusips_view_ARM_2018_04;
create view if not exists prd.fnma_missing_cusips_view_ARM_2018_04 as
select t1.cusip, t1.loan_num from
(select p.cusip, f.loan_num  from prd.fnma_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where  f.prod_type_ind = 'A' and f.current_upb>0 
) t1 
left outer join(
select cusip, loan_identifier from prd.fnmamonly 
where year=2018 and month=4 and product_type='ARM'
) t2
on(t1.cusip=t2.cusip and t1.loan_num=t2.loan_identifier)
where t2.cusip is null

2. Create View from Hive table

drop view prd.fnmamonly_view_ARM_2018_4;
create view prd.fnmamonly_view_ARM_2018_4 as
select loan_identifier as loan_num,cusip, original_upb as mortgage_loan_amount,
original_mortgage_loan_upb as orig_mort_upb,original_upb as orig_upb,
current_upb,product_type as amort_type,
original_interest_rate as orig_note_rate,
origination_interest_rate as pc_issuance_note_rate,current_interest_rate as note_rate,
original_loan_term as orig_loan_term,remaining_months_to_maturity as rem_months_to_maturity,
loan_age,interest_only_indicator as io_flag, first_p_i_payment_date as first_pi_date,
months_to_first_scheduled_amortization as months_to_amortize
from prd.fnmamonly where year=2018 and month=4 and product_type='ARM';


3. Create View from Sqoop Table

drop view prd.fnma_loan_sqoop_ARM_view;
create view prd.fnma_loan_sqoop_ARM_view as
select t1.loan_num, t1.cusip, t1.mortgage_loan_amount, t1.orig_upb,
t1.current_upb,t1.amort_type,t1.orig_note_rate,
t1.orig_loan_term,t1.rem_months_to_maturity,t1.loan_age,t1.io_flag,first_pi_date,
t1.months_to_amortize from
(select f.loan_num as loan_num,p.cusip, f.orig_loan_size as mortgage_loan_amount,
f.orig_loan_size as orig_upb,f.current_upb,concat(f.prod_type_ind,'','RM') as amort_type,
f.orig_note_rate, f.orig_loan_term,
f.rem_months_to_maturity,f.loan_age,f.io_flag,first_pi_date,
if(f.io_flag='N',null, f.months_to_amortize) as months_to_amortize
from prd.fnma_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id=p.product_id)
where  f.prod_type_ind = 'A' and f.current_upb>0 
and f.eff_date >=unix_timestamp('2018-04-01','yyyy-MM-dd')) t1
left outer join prd.fnma_missing_cusips_view_ARM_2018_04 m
on (t1.cusip=m.cusip and t1.loan_num=m.loan_num)
where m.cusip is null and m.loan_num is null

4. Do comparation
select * from prd.fnma_loan_sqoop_ARM_view a 
full outer join prd.fnmamonly_view_ARM_2018_4 b 
on a.cusip=b.cusip and a.loan_num=b.loan_num
and round(a.mortgage_loan_amount)=round(b.mortgage_loan_amount)
and round(a.orig_upb,3)=round(b.orig_upb,3)
and round(a.current_upb,3)=round(b.current_upb,3)
and round(a.orig_note_rate,3)=round(b.orig_note_rate,3)
and a.orig_loan_term=b.orig_loan_term
and a.rem_months_to_maturity=b.rem_months_to_maturity
and a.loan_age=b.loan_age
and a.io_flag=b.io_flag
and a.rem_months_to_maturity=b.rem_months_to_maturity
and nvl(a.first_pi_date,0)=nvl(b.first_pi_date,0)
where
a.loan_num is null or b.loan_num is null

 compare FNMA FRM loan
1. Create fnma_missing cusip table
drop view prd.fnma_missing_cusips_view_FRM_2018_04;
create view if not exists prd.fnma_missing_cusips_view_FRM_2018_04 as
select t1.cusip, t1.loan_num from
(select p.cusip,f.loan_num  from prd.fnma_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where  f.prod_type_ind = 'F' and f.current_upb>0 
) t1 
left outer join(
select cusip, loan_identifier from prd.fnmamonly 
where year=2018 and month=4 and product_type='FRM'
) t2
on(t1.cusip=t2.cusip and t1.loan_num=t2.loan_identifier)
where t2.cusip is null

2. Create View from Hive table

drop view prd.fnmamonly_view_FRM_2018_4;
create view prd.fnmamonly_view_FRM_2018_4 as
select loan_identifier as loan_num,cusip,original_upb as mortgage_loan_amount,
original_mortgage_loan_upb as orig_mort_upb,original_upb as orig_upb,
current_upb,product_type as amort_type,
original_interest_rate as orig_note_rate,
origination_interest_rate as pc_issuance_note_rate,current_interest_rate as note_rate,
original_loan_term as orig_loan_term,remaining_months_to_maturity as rem_months_to_maturity,
loan_age,
case
    when interest_only_indicator='YES' then 'Y'
    when interest_only_indicator='NO' then 'N'
    else null
end as io_flag, first_p_i_payment_date as first_pi_date,
months_to_first_scheduled_amortization as months_to_amortize,
modification_program as mod_prog,modification_type as mod_type,
number_of_modifications as num_of_mods,
loan_age_as_of_modification as age_asof_mod, 
days_delinquent as months_deling, loan_performance_history as loan_per_hist
from prd.fnmamonly where year=2018 and month=4 and product_type='FRM';

3. Create View from Sqoop Table
drop view prd.fnma_loan_sqoop_FRM_view;
create view prd.fnma_loan_sqoop_FRM_view as
select t1.loan_num, t1.cusip, t1.mortgage_loan_amount, t1.orig_upb,
t1.current_upb,t1.amort_type,t1.orig_note_rate,t1.pc_issuance_note_rate,
t1.orig_loan_term,t1.rem_months_to_maturity,t1.loan_age,t1.io_flag,first_pi_date,
t1.months_to_amortize,t1.mod_prog,t1.mod_type,t1.num_of_mods,t1.age_asof_mod from
(select f.loan_num,p.cusip, f.orig_loan_size as mortgage_loan_amount, 
f.orig_loan_size as orig_upb,f.current_upb,concat(f.prod_type_ind,'','RM') as amort_type,
f.orig_note_rate,f.orig_note_rate as pc_issuance_note_rate,f.orig_loan_term,
f.rem_months_to_maturity,f.loan_age,f.io_flag,f.first_pi_date,
if(f.io_flag='N',null, f.months_to_amortize) as months_to_amortize,
m.mod_program as mod_prog, m.mod_type,m.num_of_mods,
m.mod_date_loan_age as age_asof_mod 
from prd.fnma_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id=p.product_id)
left outer join prd.fnma_mod_loan_sqoop m on (f.product_id=m.product_id 
and f.loan_num=m.loan_num)
where  f.prod_type_ind = 'F' and f.current_upb>0 
and f.last_chg_date >=unix_timestamp('2018-04-01','yyyy-MM-dd')*1000 ) t1
left outer join prd.fnma_missing_cusips_view_FRM_2018_04 fm
on (t1.cusip=fm.cusip and t1.loan_num=fm.loan_num)
where fm.cusip is null and fm.loan_num is null

4. Do the comparation
select * from prd.fnma_loan_sqoop_FRM_view a 
full outer join prd.fnmamonly_view_FRM_2018_4 b 
on a.cusip=b.cusip and a.loan_num=b.loan_num
and round(a.mortgage_loan_amount,2)=round(b.mortgage_loan_amount,2)
and round(a.orig_upb,2)=round(b.orig_upb,3)
and round(a.current_upb,2)=round(b.current_upb,2)
and round(a.orig_note_rate,4)=round(b.orig_note_rate,4)
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
