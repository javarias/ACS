#! /usr/bin/env python
#*******************************************************************************
# ALMA - Atacama Large Millimiter Array
# (c) National Research Council of Canada, 2009 
# 
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
# 
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
# 
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
#
# "@(#) $Id: acsutilCASAPathInsert.py,v 1.1 2009/09/17 20:31:03 agrimstrup Exp $"
#
# who       when      what
# --------  --------  ----------------------------------------------
# agrimstrup  2009-09-08  created
#

# This helper script inserts CASA-specific directories before
# their ACS equivalents in either the PYTHONPATH or the
# LD_LIBRARY_PATH.

import os
import sys

# Normal locations of Python modules and shared libraries
SUFFIX = {'PYTHONPATH':'/lib/python2.5/site-packages',
          'LD_LIBRARY_PATH' : '/lib' }

pypath = os.environ[sys.argv[1]].split(':')
acsdir = os.environ['PYTHON_ROOT']+ SUFFIX[sys.argv[1]]
pypath.insert(pypath.index(acsdir), os.environ['CASA_ROOT']+SUFFIX[sys.argv[1]])
print ':'.join(pypath)



#
# ___oOo___
