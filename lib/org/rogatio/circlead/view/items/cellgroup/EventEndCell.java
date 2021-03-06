package org.rogatio.circlead.view.items.cellgroup;

import java.awt.Color;

import org.rogatio.circlead.view.items.CellType;
import org.rogatio.circlead.view.items.ICell;
import org.rogatio.circlead.view.items.process.bpmn.SmallLabelStyle;

import com.yworks.yfiles.geometry.PointD;
import com.yworks.yfiles.graph.INode;
import com.yworks.yfiles.graph.labelmodels.ExteriorLabelModel;
import com.yworks.yfiles.graph.styles.ShapeNodeShape;
import com.yworks.yfiles.graph.styles.ShapeNodeStyle;
import com.yworks.yfiles.view.Pen;

/**
 * The Class EventEndCell.
 */
public class EventEndCell extends CellgroupCell {

	/**
	 * Instantiates a new event end cell.
	 *
	 * @param canvas the canvas
	 */
	public EventEndCell(CellgroupCanvas canvas) {
		super(canvas);
		style = new ShapeNodeStyle();
		style.setPaint(Color.RED);
		style.setPen(Pen.getBlack());
		style.setShape(ShapeNodeShape.ELLIPSE);
	}

	/* (non-Javadoc)
	 * @see org.rogatio.circlead.view.items.DefaultCell#getType()
	 */
	@Override
	public CellType getType() {
		return CellType.EVENT_END;
	}
	
	/* (non-Javadoc)
	 * @see org.rogatio.circlead.view.items.graph.GraphCell#create()
	 */
	@Override
	public Object create() {
		//ActivityDataitem activityDataitem = (ActivityDataitem) getData("activity");
		
		canvas.setNodeSize(5);
		Color color = canvas.getColorOfRole(this.getData("roletitle").toString());
		setData("color", color);	
		
		INode n = graph.createNode(new PointD(0, 0), style);

		if (getName() != null) {
			graph.addLabel(n, getName(), ExteriorLabelModel.SOUTH, new SmallLabelStyle());
		}

		canvas.setNodeSizeDefault();
		
		setData("node", n);

		ICell rc = canvas.getCellOfRole((String) this.getData("roletitle"));

		CellgroupLink gl = new CellgroupLink(canvas);
		gl.setSource(rc);
		gl.setTarget(this);
		gl.create();
		canvas.addLink(gl);
		
		return n;
	}
}
