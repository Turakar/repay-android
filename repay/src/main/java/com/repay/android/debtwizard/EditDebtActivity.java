package com.repay.android.debtwizard;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.repay.android.R;
import com.repay.android.debtwizard.fragment.DebtFragment;
import com.repay.android.debtwizard.fragment.DebtSummaryFragment;
import com.repay.android.debtwizard.fragment.EnterAmountFragment;

import java.math.BigDecimal;

/**
 * Property of Matt Allen
 * mattallen092@gmail.com
 * http://mattallensoftware.co.uk/
 * <p/>
 * This software is distributed under the Apache v2.0 license and use
 * of the Repay name may not be used without explicit permission from the project owner.
 */

public class EditDebtActivity extends DebtActivity
{
	private int mFrame;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragmentholder);

		isEditing = true;

		mFrame = R.id.fragment;

		if (getFragmentManager().findFragmentById(mFrame) == null)
		{
			// Show fragment
			getFragmentManager().beginTransaction().replace(mFrame, new EnterAmountFragment()).commit();
		}
	}

	public void onNextButtonClick(View v)
	{
		switch (v.getId())
		{
			case R.id.fragment_enterdebtamount_donebtn:
				((DebtFragment) getFragmentManager().findFragmentById(mFrame)).saveFields();
				if (getDebtBuilder().getAmount().compareTo(BigDecimal.ZERO) > 0)
				{
					getFragmentManager().beginTransaction().replace(mFrame, new DebtSummaryFragment()).addToBackStack(null).commit();
				} else
				{
					Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.donebtn:
				((DebtFragment) getFragmentManager().findFragmentById(mFrame)).saveFields();
				save();
				break;
		}
	}
}
