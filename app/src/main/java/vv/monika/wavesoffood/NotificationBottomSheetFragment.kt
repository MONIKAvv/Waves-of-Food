package vv.monika.wavesoffood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import vv.monika.wavesoffood.adapter.NotificationAdapter
import vv.monika.wavesoffood.databinding.FragmentNotificationBottomSheetBinding


class NotificationBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottomSheetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBottomSheetBinding.inflate(inflater, container, false)

        val notifications = listOf(
            "Your Order has been Canceled",
            "Order has been taken for the delivery",
            "Congrats your Order Placed"
        )
        val notificationImage =
            listOf(R.drawable.sademoji, R.drawable.truck, R.drawable.congratulation)

        val adapter = NotificationAdapter(
            ArrayList(notifications),
            ArrayList(notificationImage)
        )
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter = adapter

        return binding.root
    }

    companion object {

    }
}