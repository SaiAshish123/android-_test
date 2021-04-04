package net.exercise.ui.movies

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_fragment.*
import net.exercise.R
import net.exercise.data.network.AlbumApi
import net.exercise.data.repositories.AlbumRepository
import net.simplifiedcoding.data.models.AlbumItem
import java.lang.reflect.Type

/**
 * @author Ashish
 */

class AlbumFragment : Fragment(), RecyclerViewClickListener{

    /**
     * Intialized all variable related for functionalities
     */


    private lateinit var factory: AlbumViewModelFactory
    private lateinit var viewModel: AlbumViewModel

    /**
     * Creating the View Instance object for the Fragment View
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_fragment, container, false)
    }

    /**
     * Here the Activity started created
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val connMgr = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connMgr.activeNetworkInfo

        /**
         * Checking the internet connection
         * if Internet is not there we will check whether data is persisted or not using shared preferences
         */
        if (networkInfo != null && networkInfo.isConnected) {
            val dialog = setProgressDialog(requireContext(), "Loading..")
            dialog.show()

            /**
             * Get Data from API if internet is available
             */

            /**
             * Intialize the API call using instantiate using retrofit Fun
             */
            val api = AlbumApi()

            /**
             * pasing the API instance object to our Repository to get the Exact Response data using Suspend Fun
             */
            val repository = AlbumRepository(api)

            /**
             * Passing the repositiory object to our View Model
             */
            factory = AlbumViewModelFactory(repository)
            /**
             * Passing the Factory Object using getContext parama with the help of AlbumViewModel Class
             */
            viewModel = ViewModelProviders.of(this, factory).get(AlbumViewModel::class.java)

            /**
             * Get the Response Object using Coroutine Suspend thread using Live Data
             */
            viewModel.getAlbums()

            /**
             * Getting Album Object using Observer patter which is implemented from Live Data using Lambda Expression in Recyclerview Adapter
             * Storing the Arraylist object using Shared Prefence
             */
            viewModel.live_album.observe(viewLifecycleOwner, Observer { albums ->
                recycler_view_movies.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    saveArrayList(albums,"Test")
                    it.adapter = AlbumsAdapter(albums, this)
                    dialog.dismiss()
                }
            })
        } else {
            /**
             * Get Data using Test Constant Keyword "Test"
             */

            val lst: List<AlbumItem?>? = getArrayList("Test")
            if (lst!=null){
                recycler_view_movies.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = AlbumsAdapter(lst as List<AlbumItem>, this)
                }
            }

            Toast.makeText(requireContext(), "Network connection is not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRecyclerViewItemClick(view: View, movie: AlbumItem) {
        when(view.id){

        }
    }

    /**
     * Saving the Data in sharedpreference key value using GSON
     */
    fun saveArrayList(list: List<AlbumItem>, key: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    /**
     * Get the Persisted Data from Shared Preference Key and value format
     */
    fun getArrayList(key: String?): List<AlbumItem?>? {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<List<AlbumItem?>?>() {}.getType()
        return gson.fromJson(json, type)
    }

    /**
     * Setting Progress Dialog Layout Usage
     */
    fun setProgressDialog(context:Context, message:String): AlertDialog {
        val llPadding = 30
        val ll = LinearLayout(context)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam

        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam

        llParam = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(context)
        tvText.text = message
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20.toFloat()
        tvText.layoutParams = llParam

        ll.addView(progressBar)
        ll.addView(tvText)

        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        builder.setView(ll)

        val dialog = builder.create()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window?.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window?.attributes = layoutParams
        }
        return dialog
    }

}
