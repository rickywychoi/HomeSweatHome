package com.example.homesweathome;

import android.app.Activity;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

    import java.util.List;




    public class RegimenAdapter extends ArrayAdapter<Regimen> {

        private Activity context;
        private List<Regimen> regimenList;

        public RegimenAdapter(Context context, int resource, List<Regimen> objects, Activity context1, List<Regimen> regList) {
            super(context, resource, objects);
            this.context = context1;
            this.regimenList = regList;
        }

        public RegimenAdapter(Activity context, List<Regimen> regimenList) {
            super(context, R.layout.activity_new_workout_list, regimenList);
            this.context = context;
            this.regimenList = regimenList;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.new_regimen_list, null, true);

        TextView workoutName = listViewItem.findViewById(R.id.regimenName);

        Regimen reg = regimenList.get(position);
        workoutName.setText(reg.getTitle());

        return listViewItem;
    }

}
