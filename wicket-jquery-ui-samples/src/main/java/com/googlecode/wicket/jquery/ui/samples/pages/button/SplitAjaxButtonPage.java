package com.googlecode.wicket.jquery.ui.samples.pages.button;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import com.googlecode.wicket.jquery.ui.JQueryIcon;
import com.googlecode.wicket.jquery.ui.form.button.SplitAjaxButton;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.menu.IMenuItem;
import com.googlecode.wicket.jquery.ui.widget.menu.MenuItem;

public class SplitAjaxButtonPage extends AbstractButtonPage
{
	private static final long serialVersionUID = 1L;

	private final FeedbackPanel feedback;

	public SplitAjaxButtonPage()
	{
		final Form<Void> form = new Form<Void>("form") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit()
			{
				info("Form submitted");
			}
		};

		this.add(form);

		// FeedbackPanel //
		this.feedback = new JQueryFeedbackPanel("feedback");
		form.add(this.feedback.setOutputMarkupId(true));

		// TextField //
		final RequiredTextField<String> textField = new RequiredTextField<String>("text", new Model<String>());
		form.add(textField);

		// Buttons //
		form.add(new SplitAjaxButton("button", newMenuList()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
				this.error("Validation failed!");
				target.add(feedback);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, IMenuItem item)
			{
				this.info(String.format("%s: %s",item.getTitle().getObject(), textField.getModelObject()));
				target.add(feedback);
			}
		});
	}

	private List<IMenuItem> newMenuList()
	{
		List<IMenuItem> list = new ArrayList<IMenuItem>();

		list.add(new MenuItem("View", JQueryIcon.SEARCH) {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick(AjaxRequestTarget target)
			{
				info("Selected " + this.getTitle().getObject());
				target.add(feedback);
			}
		});

		list.add(new MenuItem("Edit", JQueryIcon.WRENCH) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				info("Selected " + this.getTitle().getObject());
				target.add(feedback);
			}
		});

		list.add(new MenuItem("Delete", JQueryIcon.NOTICE) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				info("Selected " + this.getTitle().getObject());
				target.add(feedback);
			}
		});

		return list;
	}
}
