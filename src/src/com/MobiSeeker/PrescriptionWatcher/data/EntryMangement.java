package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;
import android.widget.Toast;

import com.MobiSeeker.PrescriptionWatcher.R;

public class EntryMangement {

	public void saveEntry(Entry entry,Context context)
	{
		 PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
	        try {
	            prescriptionRepository.save(context, entry);
	            Toast.makeText(context, context.getString(R.string.addingPrescriptionSucceeded), Toast.LENGTH_SHORT).show();

	        } catch (UnsupportedOperationException exception) {
	            Toast.makeText(context, context.getString(R.string.addingPrescriptionFailed) + " " + exception.getMessage(), Toast.LENGTH_LONG).show();

	        } catch (Exception exception) {
	        }

		
	}
	
}
