package com.ahmedderbala.espoir.Cases;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedderbala.espoir.R;
import com.ahmedderbala.espoir.activities.MainActivity;

import com.ahmedderbala.espoir.app.AppController;
import com.ahmedderbala.espoir.helper.JsonArrayPostRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ahmedderbala.espoir.app.AppConfig.URL_LIST_CASES;

/**
 * A simple {@link Fragment} subclass.
 */
public class CasesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CasesAdapter adapter;
    private List<Case> caseList;
    JsonArrayPostRequest jsonArrayRequest ;



    public CasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cases, container, false);

        listCases();


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        caseList = new ArrayList<>();
        adapter = new CasesAdapter(getContext(), caseList);

        /*RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);

        //prepareAlbums();


        // Inflate the layout for this fragment
        return rootView;
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void listCases()
    {
        Log.d("URL", URL_LIST_CASES);
        HashMap<String, String> params = new HashMap<String, String>();
        jsonArrayRequest = new JsonArrayPostRequest(URL_LIST_CASES,
                new Response.Listener<JSONArray>()
                {

                    @Override
                    public void onResponse(JSONArray array) {
                        for(int i = 0; i<array.length(); i++) {
                            Case GetDataAdapter = new Case();

                            JSONObject json = null;
                            try {
                                json = array.getJSONObject(i);
                                GetDataAdapter.setTitle(json.getString("title"));
                                GetDataAdapter.setShortDescription(json.getString("shortDescription"));
                                GetDataAdapter.setLongDescription(json.getString("longDescription"));
                                GetDataAdapter.setThumbnail(json.getString("thumbnail"));
                                GetDataAdapter.setAuthor(json.getString("author"));
                                GetDataAdapter.setGovernorate(json.getString("governorate"));
                                GetDataAdapter.setCity(json.getString("city"));
                                GetDataAdapter.setPlace(json.getString("place"));




                            }
                            catch (JSONException e)
                            {

                                e.printStackTrace();
                            }

                            caseList.add(GetDataAdapter);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getActivity(), "Pas de favoris", Toast.LENGTH_LONG).show();
                Log.d("PLACETIITLE", "onResponse: "+error.getMessage());
            }
        },params);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, "hhh");
    }

}
